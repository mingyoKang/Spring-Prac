package spring;

public class MemberInfoPrinter {
	// 지정한 이메일을 갖는 Member를 찾아 정보를 콘솔에 출력한다
	
	private MemberDao memberDao;
	private MemberPrinter memberPrinter;
	
	public void printMemberInfo(String email) {
		
		Member member = memberDao.selectByEmail(email);
		
		if(member == null) {
			
			System.out.println("일치하는 회원 정보가 없습니다.");
			return;
			
		}
		
		memberPrinter.print(member);
		System.out.println();
	}
	
	public void setMemberDao(MemberDao memberDao) {
		
		this.memberDao = memberDao;
		
	}
	
	public void setPrinter(MemberPrinter memberPrinter) {
		
		this.memberPrinter = memberPrinter;
		
	}

}
