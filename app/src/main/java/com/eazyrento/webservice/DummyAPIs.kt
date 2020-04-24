/*
package com.rental.webservice

import com.google.gson.JsonElement
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.*


interface APIServicesBeerbduy {
    */
/*
    * Get Api of Category List for Menu
    * *//*

    @GET("api/place/nearbysearch/json")
    fun categoryListService(@Query("location", encoded = true) latitude_longitude: String?,
        //@Query("region", encoded = true) region: String?,
                            @Query("radius", encoded = true) radius: String?,
                            @Query("type", encoded = true) type: String?,
                            @Query("key", encoded = true) key: String?): Observable<GooglePlaceApi>




    */
/*
  * Get Api of Category List for Menu
  * *//*

    @GET("api/place/textsearch/json")
    fun categoryListServiceList(@Query("location", encoded = true) latitude_longitude: String?,
        //@Query("region", encoded = true) region: String?,
                                @Query("radius", encoded = true) radius: String?,
                                @Query("type", encoded = true) type: String?,
                                @Query("key", encoded = true) key: String?,
                                @Query("query", encoded = true) query: String?
    ): Observable<GooglePlaceApi>

    */
/*
 * Get Api of Category List for Menu
 * *//*

    @GET("api/place/textsearch/json")
    fun categoryListServiceWithoutTypeList(@Query("location", encoded = true) latitude_longitude: String?,
        //@Query("region", encoded = true) region: String?,
                                           @Query("radius", encoded = true) radius: String?,
                                           @Query("key", encoded = true) key: String?,
                                           @Query("query", encoded = true) query: String?
    ): Observable<GooglePlaceApi>




    */
/*
    * Get Api of Category List for Menu
    * *//*

    @GET("api/place/nearbysearch/json")
    fun categoryListServicePagination(@Query("pagetoken", encoded = true) pagetoken: String?,
                                      @Query("key", encoded = true) key: String?): Observable<GooglePlaceApi>

    */
/*
    * Post APi for login
    * *//*

    @POST("users/login/")
    fun login(@Body json: LoginRequest): Observable<JsonElement>

    */
/*
 * Post APi for register
 * *//*

    @POST("users/register/")
    fun register(@Body json: RegisterRequest): Observable<JsonElement>


    */
/*
    * Post APi for Social
    * *//*

    @POST("users/login/")
    fun social(@Body json: SocialRequest): Observable<JsonElement>


    */
/*
    * Post APi for register
    * *//*

    @POST("users/verify-passcode/")
    fun otp(@Body json: OTPRequest): Observable<JsonElement>

    */
/*
    * Post APi for forgot
    * *//*

    @POST("users/forgot/password/")
    fun forgot(@Body json: ForgotRequest): Observable<JsonElement>

    */
/*
    * Post APi for reset
    * *//*

    @POST("users/reset/password/")
    fun reset(@Body json: ResetPasswordRequest): Observable<JsonElement>

    */
/*
    * Post APi for logout
    * *//*

    @POST("users/logout/")
    fun logout(): Observable<JsonElement>

    */
/*
    * Post APi for find bear shps buddies
    * users/checkedin_buddies/
    * *//*

    @POST("beershops/find_beers/")
    fun findBearshopsBuddies(@Body it : FindBearShopsPost): Observable<FindBeerBuddiesInDB>

    */
/*
    * Post APi for find bear shps buddies
    * users/checkedin_buddies/
    * *//*

    @POST("beershops/save_beers/")
    fun saveBearshopsBuddies(@Body it : SaveBeerBuddiesInDB): Observable<JsonElement>

    */
/*
    * Post APi for checkInBuddies
    * users/checkedin_buddies/
    * *//*

    @POST("users/checkedin_buddies/")
    fun checkInBuddies(): Observable<CheckedInBuddies>

    */
/*
    * Post APi for Dashboard
    * *//*

    @POST("beershops/add_beerdetail/")
    fun dashboard(@Body it: MutableSet<Result?>?): Observable<BarUserContactList>

    */
/*
    * Post APi for Dashboard
    * *//*

    @POST("api/place/details/json")
    fun placeDetailImageList(@Query("placeid", encoded = true) place_id: String?, @Query("fields", encoded = true) fields: String?,
                             @Query("key", encoded = true) key: String?): Observable<ImageDetailList>

    */
/*
    * Post APi for user profile
    * *//*

    @GET("users/edit_profile/")
    fun getProfile(): Observable<ProfileResponseModel>
    */
/*
    * Post APi for other user profile
    * *//*

    @GET("users/other_user_profile/{id}/")
    fun getOtherUserProfile(@Path("id") id: Int): Observable<OtherUserProfile>

    */
/*
    * Post APi for Dashboard
    * *//*

    @POST("users/contact_list/")
    fun sendContact(@Body list: MutableList<UserContactInfo>): Observable<JsonElement>

    */
/*
    * Post APi for Check In
    * *//*

    @POST("beershops/beer_checkin/")
    fun checkIn(@Body list: CheckIn): Observable<CheckInResponse>

    */
/*
    * Post APi for Check In
    * *//*

    @GET("users/contact_list/")
    fun getContactList(): Observable<JsonElement>

    */
/*
    * Post APi for Favourite
    * *//*

    @POST("beershops/user_favourite/")
    fun setFavourite(@Body favourite: Favourite): Observable<JsonElement>

    */
/*
    * Post APi for Favourite list
    * *//*

    @GET("beershops/user_favourite/")
    fun getFavourite(): Observable<FavouritesListResponse>

    */
/*
    * Post APi for Favourite list
    * *//*

    @POST("beershops/rating/")
    fun sendFeedback(@Body rating: RatingRequest): Observable<JsonElement>

    */
/*
    * Post APi for Favourite list
    * *//*

    @GET("beershops/rating/")
    fun getFeedback(@Query("id") checkOut: Int): Observable<UserRatingListResponse>

    */
/*
    * Post APi for Favourite list
    * *//*

    @POST("users/forget_verify_passcode/")
    fun forgotVerifyPasscode(@Body d: OTPRequest): Observable<JsonElement>

    */
/*
    * Post APi for Favourite list
    * *//*

    @POST("users/social_phone_update/")
    fun socialMobile(@Body d: UpdateSocial): Observable<JsonElement>

    */
/*
    * Post APi for Favourite list
    * *//*

    @POST("users/verify_social_passcode/")
    fun socialMobileUpdate(@Body d: OTPRequest): Observable<JsonElement>

    */
/*
     * Post APi for Favourite list
     * *//*

    @GET("users/friend_request")
    fun connectionStatus(@Query("status") checkOut: String): Observable<PendingFriendResponse>

    */
/*
     * Get APi for contact friend list
     * *//*

    @GET("users/contact_list/")
    fun getContact(): Observable<ContactListModel>

    */
/*
     * Post APi for sending Friend request
     * *//*

    @POST("users/friend_request/")
    fun sendFriendReq(@Body s: FriendRequestSendModel): Observable<JsonElement>

    */
/*
     * Post APi for sending Friend request
     * *//*

    @POST("events/invites/")
    fun sendInvite(@Body s: SendInviteRequest): Observable<JsonElement>

    */
/*
     * Post APi for Resend OTP
     * *//*

    @POST("users/resend_otp/")
    fun resend(@Body s: ResendOtp): Observable<JsonElement>

    */
/*
     * Post APi for sending Friend request
     * *//*

    @GET("events/user/events/")
    fun getEventList(): Observable<EventMainResponse>

    */
/*
     * Post APi for delete Event
     * *//*

    @DELETE("events/delete/event/{event_id}/")
    fun deleteEvent(@Path("event_id") id: Int): Observable<JsonElement>



    */
/*
* Post APi for remove event  http://127.0.0.1:8000/api/events/event/remove/
* *//*


    @POST("events/event/remove/")
    fun removeEvent(@Body name: EventRemoveRequest?): Observable<EventRemoveResponse>

    ///api/events/delete/event/18/
    */
/*
     * PUT APi for Clear All notification
     * *//*

    @PUT("notification/delete_notifications/")
    fun clear(@Body checkOut: ClearNotification): Observable<JsonElement>

    */
/*
     * PUT APi for decline Event
     * *//*

    @PUT("events/invites/")
    fun event(@Body checkOut: EventDecline): Observable<JsonElement>
    */
/*
     * PUT APi for decline Event
     * *//*

    @PUT("users/friend_request/")
    fun putFriendRequest(@Body checkOut: FriendRequestSendModel): Observable<JsonElement>
    */
/*
        * PUT APi for Setting Api
        * *//*

    @PUT("users/user_settings/")
    fun setting(@Body checkOut: Setting): Observable<JsonElement>

    */
/*
     * GET APi for Invited Buddy Event
     * *//*

    @GET("events/event/details/")
    fun getInvitedBuddyList(@Query("event_id") id: Int): Observable<InvitedBuddiesList>

    */
/*
     * GET APi for searchFriend
     * *//*

    @GET("users/friend_search/")
    fun searchFriend(@Query("search") search: String): Observable<ContactListModel>

    */
/*
     * GET APi for searchFriend
     * *//*

    @GET("notification/notifications/")
    fun notificationList(): Observable<NotificationList>

    @Multipart
    @PUT("users/edit_profile/")
    fun upload(
        @Part("name") name: RequestBody,
        @Part("date_of_birth") date_of_birth: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("email") email: RequestBody,
        @Part("city") city: RequestBody,
        @Part("state") state: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<JsonElement>

    */
/*
   * Post APi for Check Out
   * *//*

*/
/*    @Multipart
    @POST("beershops/beer_checkout/")
//    fun checkOut(@Body list: CheckOut):Observable<JsonElement>
    fun checkOut(@Part("beer_id") name: RequestBody,
                 @Part("check_out_status") state: RequestBody,
                 @Part images: List<MultipartBody.Part>
    ): Observable<JsonElement>*//*



    */
/*
 * Post APi for Check Out
 * *//*

    @Multipart
    @POST("beershops/beer_checkout/")
    fun checkOut(@Part("beer_id") name: RequestBody,
                 @Part("check_out_status") state: RequestBody,
                 @Part images: List<MultipartBody.Part>
    ): Observable<JsonElement>

    */
/*
    * GET APi Comment List
    * *//*

    @GET("events/event/comment/")
    fun getCommentList(@Query("event_id") event_id:Int?): Observable<CommentMainResponse>

    */
/*
   * Post   Comment API
   * *//*

    @POST("events/event/comment/")
    fun postCommentList(@Body mCommentPostApi:CommentPostApi?): Observable<JsonElement>

}*/
