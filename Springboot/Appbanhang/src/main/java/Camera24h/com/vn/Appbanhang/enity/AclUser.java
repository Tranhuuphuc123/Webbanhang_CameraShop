    package Camera24h.com.vn.Appbanhang.enity;
/* class Enity này dùng mapping đến co sở dữ liệu bên MySQl*/

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

// class Enity AclUser ánh xạ mapping đến bảng table acl_sers bên MySQL
//@Enity danh dau cho code biet day khong phai class thong thuong ma class Enity
@Entity
@Table(name = "acl_users")
public class AclUser {

    //@Id danh dau la khoa chinh tu tang
    // @GeneratedValue(strategy = GenerationType.IDENTITY) giai thua tang theo Identity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String last_name;
    private String first_name;
    private String email;

    //@Nullable cho phép đc null vì trong csdl mình cho notnull
    @Nullable
    private String avatar;
    @Nullable
    private String job_title;
    @Nullable
    private String department;
    @Nullable
    private Integer manager_id;

    private String phone;
    private String address1;

    @Nullable
    private String address2;
    @Nullable
    private String city;
    @Nullable
    private String state;
    @Nullable
    private String postal_code;
    @Nullable
    private String country;
    @Nullable
    private String remember_token;
    @Nullable
    private String active_code;

    private Long status;

    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private Date updated_at;



    //getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //@Nullable là xác thực th csdl có thẻ dc null(đc phép đê trống)
    @Nullable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(@Nullable String avatar) {
        this.avatar = avatar;
    }

    @Nullable
    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(@Nullable String job_title) {
        this.job_title = job_title;
    }

    @Nullable
    public String getDepartment() {
        return department;
    }

    public void setDepartment(@Nullable String department) {
        this.department = department;
    }

    @Nullable
    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(@Nullable Integer manager_id) {
        this.manager_id = manager_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Nullable
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(@Nullable String address2) {
        this.address2 = address2;
    }

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }

    @Nullable
    public String getState() {
        return state;
    }

    public void setState(@Nullable String state) {
        this.state = state;
    }

    @Nullable
    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(@Nullable String postal_code) {
        this.postal_code = postal_code;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    public void setCountry(@Nullable String country) {
        this.country = country;
    }

    @Nullable
    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(@Nullable String remember_token) {
        this.remember_token = remember_token;
    }

    @Nullable
    public String getActive_code() {
        return active_code;
    }

    public void setActive_code(@Nullable String active_code) {
        this.active_code = active_code;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Nullable
    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(@Nullable LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Nullable
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(@Nullable Date updated_at) {
        this.updated_at = updated_at;
    }
}
