package member.vo;

public class MemberVo {
	String id;
	private String pw;
	private String name;
	private String ssn;
	private String hp;
	private String email;
	
	public MemberVo() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberVo(String id, String pw, String name, String ssn, String hp, String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.ssn = ssn;
		this.hp = hp;
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", name=" + name + ", ssn=" + ssn + ", hp=" + hp + ", email="
				+ email + "]";
	}

}
