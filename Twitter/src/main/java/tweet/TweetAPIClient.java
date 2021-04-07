package tweet;

import base.RestAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT="/statuses/home_timeline.json";
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String GET_MENTION_TIMELINE_ENDPOINT = "/mentions_timeline.json";
    private final String GET_FAVORITELIST_ENDPOINT = "/mentions_timeline.json";
    private final String POST_FAVORITES_ENDPOINT = "/favorites/create.json";
    private final String POST_UNFAVORITE_ENDPOINT = "/favorites/destroy.json";
    private final String POST_UNTWEET_RETWEET = "/statuses/unretweet.json";
    private final String GET_STATUSES = "/statuses/show.json";
    private final String GET_FRIENDS = "/friends/ids.json?screen_name=IsratReto";
    private final String POST_CREATE_LIST = "/lists/create.json";
    private final String GET_LIST = "/lists/list.json";
    private final String POST_CREATE_COLLECTION_LIST = "/collections/create.json";
    private final String POST_ADD_TO_COLLECTION = "/collections/entries/add.json";
    private final String POST_DESTROY_COLLECTION = "/collections/destroy.json";
    private final String GET_ACCOUNT_SETTINGS = "/account/settings.json";
    private final String GET_FRIENDS_LIST = "/friends/list.json";




    // GET all Tweet Information

    public ValidatableResponse getUserTimeTweet(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }


    // Delete a tweet from user twitter
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUrl+this.DELETE_TWEET_ENDPOINT).then();
    }




    // Response Time check
    public ValidatableResponse responseTime(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then();

    }

    // Header Value
    public void headerValue(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then().extract().headers());

    }

    public  void checkProperty(){
        Response response= given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator= response.jsonPath();
        String createdAt=pathEvaluator.get("id");
        System.out.println(createdAt);
    }

//create a retweet
    public ValidatableResponse createReTweet(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", reTweetId)
                .when().post(this.baseUrl+this.CREATE_RETWEET_ENDPOINT)
                .then();
    }

    //untweet a retweet
    public ValidatableResponse unReTweet(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", reTweetId)
                .when().post(this.baseUrl+this.POST_UNTWEET_RETWEET)
                .then();
    }


//favorite a tweet
    public ValidatableResponse favoriteTweet(Long tweetID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().post(this.baseUrl + this.POST_FAVORITES_ENDPOINT)
                .then();
    }

    //Unfavorite a tweet
    public ValidatableResponse unfavoriteTweet(Long tweetID) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().post(this.baseUrl + this.POST_UNFAVORITE_ENDPOINT)
                .then();
    }

    //get statuses
    public ValidatableResponse getStatuses(Long tweetID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetID)
                .when().get(this.baseUrl + this.GET_STATUSES)
                .then();
    }
    //get all ids of other user tweets
    public ValidatableResponse getFriendsId(String screenName){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("screen_name", screenName)
                .when().get(this.baseUrl + this.GET_FRIENDS)
                .then();
    }

    //create list using post
    public ValidatableResponse createList(String name){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name)
                .when().post(this.baseUrl + this.POST_CREATE_LIST)
                .then();
    }
    //get list using get
    public ValidatableResponse getList(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_LIST)
                .then();
    }
    //create collection list
    public ValidatableResponse createCollectionList(String name){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("name", name)
                .when().post(this.baseUrl + this.POST_CREATE_COLLECTION_LIST)
                .then();
    }
//add tweet to collection
    public ValidatableResponse addToCollectionList( String collectionID, Long tweetID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//                .param("name", name)
                .param("id", collectionID)
                .param("tweet_id", tweetID)
                .when().post(this.baseUrl + this.POST_ADD_TO_COLLECTION)
                .then();
    }

    //destroy collection list
    public ValidatableResponse destroyCollectionList(String customID){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", customID)
                .when().post(this.baseUrl + this.POST_DESTROY_COLLECTION)
                .then();
    }

    //get account settings info
    public ValidatableResponse getAccountSettings(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_ACCOUNT_SETTINGS)
                .then();
    }

    //Get friends list
    public ValidatableResponse getFriendsList(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_FRIENDS_LIST)
                .then();
    }






    //mention on another timeline
    public ValidatableResponse mentionOnTimeline(long count, String tweet){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", count).param("status",tweet)
                .when().get(this.baseUrl+this.GET_MENTION_TIMELINE_ENDPOINT)
                .then();
    }













//    public ValidatableResponse favoritelist(String li){
//        ArrayList<String> text = new ArrayList<>();
//
//        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
//    }





}