package com.qa.tests.restApi;

import com.qa.api.StatusCode;
import com.qa.api.applicationApi.SignInApi;
import com.qa.api.applicationApi.PlaylistApi;
import com.qa.pojo.playlist.Error;
import com.qa.pojo.playlist.Playlist;
import com.qa.pojo.registration.SignInRequest;
import com.qa.pojo.registration.SignInResponse;
import com.qa.tests.BaseTest;
import com.qa.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.qa.utils.FakerUtils.generateDescription;
import static com.qa.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class SignInTest extends BaseTest {

    @Story("Story- Register a user")
    @Test
    public void shouldBeAbleToLoginSuccessfully(){
//        LoginRequest loginRequest = loginRequestBuilder(generateName(), generateDescription(), false);
        SignInRequest signInRequest = signInRequestBuilder("ashutosh", "Test@1234", true);
        Response response = SignInApi.post(signInRequest);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertLoginResponse(response.as(SignInResponse.class));
    }

    @Story("Story- Get a playlist")
    @Link("Link- https://example.com")
    @Link(name = "allure", type = "mylink")
    @TmsLink("TC_1235")
    @Issue("Issue_53572")
    @Description("This test will fetch a playlist for the provided playlistId")
    @Test(description = "should be able to get a playlist")
//    public void shouldBeAbleToGetAPlaylist(){
//        Playlist requestPlaylist = playlistBuilder("New Playlist", "New Playlist Description", true);
//        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
//        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
//        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
//    }
//
//    @Story("Story- Update a playlist")
//    @Test
//    public void shouldBeAbleToUpdateAPlaylist(){
//        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
//        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
//        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
//    }
//
//    @Story("Story- Create a playlist")
//    @Test
//    public void shouldNotBeAbleToCreateAPlaylistWithoutName(){
//        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
//        Response response = PlaylistApi.post(requestPlaylist);
//        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
//        assertError(response.as(Error.class), StatusCode.CODE_400);
//    }
//
//    @Story("Story- Create a playlist")
//    @Test
//    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
//        String invalidToken = "123456";
//        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
//        Response response = PlaylistApi.post(invalidToken, requestPlaylist);
//        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
//        assertError(response.as(Error.class), StatusCode.CODE_401);
//    }

    @Step
    public SignInRequest signInRequestBuilder(String username, String password, boolean rememberMe) {
        return new SignInRequest()
                .setUserName(username)
                .setPassword(password)
                .setRememberMe(rememberMe);
    }

    @Step
    public void assertLoginResponse(SignInResponse signInResponse){
            assertThat(signInResponse.getMessage(), equalTo("Success"));
        assertThat(signInResponse.getApiResponseStatus(), equalTo("OK"));
        assertThat(signInResponse.getHttpStatus(), equalTo(200));
        assertThat(signInResponse.getData().getAccessToken(), notNullValue());
        assertThat(signInResponse.getData().getRefreshToken(), notNullValue());
        assertThat(signInResponse.getData().getExpiresIn(), equalTo(3600));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }
}
