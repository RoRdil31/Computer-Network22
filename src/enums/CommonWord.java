/*
 * 사용하는 단어들을 enum으로 저장
 */
package enums;


public enum CommonWord {

  SIGN_UP_MEMBERSHIP("회원가입", 0),
  LOGIN("로그인", 1),
  ID("아이디", 2),
  PASSWORD("비밀번호", 3),
  NAME("이름", 4),
  NICKNAME("별명", 5),
  EMAIL("이메일", 6),
  BIRTH("생년원일", 7),  
  MYPROFILE("내 프로필", 8),
  FRIEND("친구", 9),
  GOBACK("뒤로가기", 10),
  ADD("친구추가",11),
  API("코로나 확진자수",12),
  ONLINE("온라인 친구",13);


  private final String text;

  private final int num;


  CommonWord(String text, int num) {

    this.text = text;
    this.num = num;
  }

  public String getText() {

    return text;
  }

  public int getNum() {

    return num;
  }


}
