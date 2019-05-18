package com.example.projectui.entities;

public class DonnerPojo {

   private String name, mobile,email,age,time,gender,bloodType, country, paymentType,longitude,latitude;

   public DonnerPojo(String name, String mobile, String email, String age, String time, String gender, String bloodType, String country, String paymentType, String longitude, String latitude) {
      this.name = name;
      this.mobile = mobile;
      this.email = email;
      this.age = age;
      this.time = time;
      this.gender = gender;
      this.bloodType = bloodType;
      this.country = country;
      this.paymentType = paymentType;
      this.longitude = longitude;
      this.latitude = latitude;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getBloodType() {
      return bloodType;
   }

   public void setBloodType(String bloodType) {
      this.bloodType = bloodType;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getPaymentType() {
      return paymentType;
   }

   public void setPaymentType(String paymentType) {
      this.paymentType = paymentType;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }
}
