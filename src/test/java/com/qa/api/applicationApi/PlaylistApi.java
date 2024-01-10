package com.qa.api.applicationApi;

import com.qa.api.RestResource;
import com.qa.pojo.playlist.Playlist;
import com.qa.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.qa.api.Route.PLAYLISTS;
import static com.qa.api.Route.USERS;
import static com.qa.api.TokenManager.getToken;

public class PlaylistApi {

    @Step
    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, getToken(), requestPlaylist);
    }

    @Step
    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, token, requestPlaylist);
    }

    @Step
    public static Response get(String playlistId){
        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());
    }

    @Step
    public static Response put(String playlistId, Playlist requestPlaylist){
        return RestResource.put(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }
}
