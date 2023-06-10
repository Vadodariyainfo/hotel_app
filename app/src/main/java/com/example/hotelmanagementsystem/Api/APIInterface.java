package com.example.hotelmanagementsystem.Api;

import com.example.hotelmanagementsystem.Model.AllRoomModel;
import com.example.hotelmanagementsystem.Model.CategoriesGalleryModel;
import com.example.hotelmanagementsystem.Model.ContactUsModel;
import com.example.hotelmanagementsystem.Model.ForgotPasswordModel;
import com.example.hotelmanagementsystem.Model.GalleryModel;
import com.example.hotelmanagementsystem.Model.LoginModel;
import com.example.hotelmanagementsystem.Model.ProfileModel;
import com.example.hotelmanagementsystem.Model.ProfileUpdateModel;
import com.example.hotelmanagementsystem.Model.RatingModel;
import com.example.hotelmanagementsystem.Model.RegistrationModel;
import com.example.hotelmanagementsystem.Model.RoomBookingModel;
import com.example.hotelmanagementsystem.Model.SingleRoomModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("api.php?users_login")
    Call<LoginModel> GetLogin(@Query("email") String email, @Query("password") String password);

    @GET("api.php?user_register")
    Call<RegistrationModel> GetRegistration(@Query("name") String name, @Query("phone") String phone, @Query("email") String email, @Query("password") String password);

    @GET("api.php?forgot_pass")
    Call<ForgotPasswordModel> GetForgotPassword(@Query("email") String email);

    @GET("api_contact.php?")
    Call<ContactUsModel> GetContactUs(@Query("name") String name, @Query("phone") String phone, @Query("email") String email, @Query("subject") String subject, @Query("message") String message);

    @GET("api.php?room_list")
    Call<AllRoomModel> GetAllRoom();

    @GET("api.php?")
    Call<SingleRoomModel> GetSingleRoomDetails(@Query("room_id") String room_id);

    @GET("api_booking.php?")
    Call<RoomBookingModel> GetRoomBooking(@Query("name") String name, @Query("phone") String phone, @Query("email") String email, @Query("adults") String adults, @Query("children") String children, @Query("check_in_date") String check_in_date, @Query("check_out_date") String check_out_date);

    @GET("api_rating.php?")
    Call<RatingModel> GetRatingModel(@Query("room_id") String room_id,@Query("device_id") String device_id,@Query("user_id") String user_id,@Query("rate") String rate,@Query("message") String message);
    @GET("api.php?user_profile")
    Call<ProfileModel> GetProfileModel(@Query("id") String id);

    @GET("api.php?user_profile_update")
    Call<ProfileUpdateModel> GetProfileUpdateModel(@Query("user_id") String user_id, @Query("name") String name, @Query("phone") String phone, @Query("email") String email, @Query("password") String password);

    @GET("api.php?cat_list")
    Call<CategoriesGalleryModel> GetCategoriesGalleryModel();

    @GET("api.php?")
    Call<GalleryModel> GetGalleryModel(@Query("cat_id") String cat_id);
}
