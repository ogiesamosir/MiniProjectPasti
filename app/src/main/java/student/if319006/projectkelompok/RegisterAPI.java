package student.if319006.projectkelompok;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RegisterAPI {

    @FormUrlEncoded
    @POST("/tambah.php")
    Call<Value> daftar(@Field("nama") String nama,
                       @Field("harga") String harga);

    @GET("lihat.php")
    Call<Value> view();


    @FormUrlEncoded
    @POST("ubah.php")
    Call<Value> ubah(@Field("id") String id,
                     @Field("nama") String nama,
                     @Field("harga") String harga);

    @FormUrlEncoded
    @POST("hapus.php")
    Call<Value> hapus(@Field("id") String id);





}
