/*
 * 사용자정보 확인 및 저장
 */
package server.userdb;


public class User {
	
  private String uid;
  
  private String password;
  
  private String uname;
	
  private String unickname;

  private String uemail;

  private String ubirth;
  


  public User(String uid, String password, String uname,
		  String unickname, String uemail, String ubirth) {

	this.uid =  uid;
	this.password = password;
	this.uname = uname; 
    this.unickname = unickname;
    this.uemail = uemail;
    this.ubirth = ubirth;

   
  }
  public String getUid() {
	  return uid;
  }
  
  public void setUid(String uid) {
	  this.uid = uid;
  }

  public String getUname() {

    return uname;
  }

  public void setUname(String uname) {

    this.uname = uname;
  }

  public String getUnickname() {
	  return unickname;
  }
  
  public void setUnickname(String unickname) {
	  this.unickname = unickname;
  }
  
  public String getUemail() {

    return uemail;
  }

  public void setUemail(String uemail) {

    this.uemail = uemail;
  }

  public String getUbirth() {
	  return ubirth;
  }
  
  public void setUbirth(String ubirth) {
	  this.ubirth = ubirth;
  }
  
  public String getPassword() {

    return password;
  }
  
  public void setPassword(String password) {

    this.password = password;
  }


}
