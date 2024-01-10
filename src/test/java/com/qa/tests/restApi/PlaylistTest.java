package com.qa.tests.restApi;

import com.qa.api.StatusCode;
import com.qa.api.applicationApi.PlaylistApi;
import com.qa.pojo.playlist.Error;
import com.qa.pojo.playlist.Playlist;
import com.qa.tests.BaseTest;
import com.qa.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.qa.utils.FakerUtils.generateDescription;
import static com.qa.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Epic- Spotify OAuth 2.0")
@Feature("Feature- Playlist API")
public class PlaylistTest extends BaseTest {

    @Story("Story- Create a playlist")
    @Test
    public void shouldBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Story- Get a playlist")
    @Link("Link- https://example.com")
    @Link(name = "allure", type = "mylink")
    @TmsLink("TC_1235")
    @Issue("Issue_53572")
    @Description("This test will fetch a playlist for the provided playlistId")
    @Test(description = "should be able to get a playlist")
    public void shouldBeAbleToGetAPlaylist(){
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New Playlist Description", true);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Story- Update a playlist")
    @Test
    public void shouldBeAbleToUpdateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Story("Story- Create a playlist")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Story- Create a playlist")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalidToken = "123456";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalidToken, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return new Playlist()
                .setName(name)
                .setDescription(description)
                .setPublic(_public);
//        return Playlist.builder()
//                .name(name)
//                .description(description)
//                ._public(_public)
//                .build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
//        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
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
