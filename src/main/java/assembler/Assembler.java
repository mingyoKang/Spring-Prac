package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
	// 객체를 생성하고 의존 객체를 주입해주는 클래스
	// 특정한 객체가 필요한 곳에 객체를 제공
	// Spring이 이와 같은 조립기 역할을 한다.
	
	private MemberDao memberDao;
	private MemberRegisterService regService;
	private ChangePasswordService pwdService;
	
	public Assembler() {
		
		memberDao = new MemberDao();
		regService = new MemberRegisterService(memberDao);
		pwdService = new ChangePasswordService();
		pwdService.setMemberDao(memberDao);
		
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regService;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdService;
	}
}
