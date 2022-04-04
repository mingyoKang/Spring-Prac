package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppConf02 {
	// 스프링을 이용해서 어플리케이션을 개발하다보면 적게는 수십 개에서 많게는 수백여 개 이상의 빈을 설정하게 된다.
	// 설정하는 빈의 개수가 증가하면 한 개의 클래스 파일에 설정하는 것보다 영역별로 설정 파일을 나눠 관리하면 편하다.
	// 그래서 AppConf01과 AppConf02로 나눠 관리하는 것이다.
	// 스프링은 한 개 이상의 설정 파일을 이용해 컨테이너를 생성할 수 있다.
	
	// Autowired 애노테이션은 스프링의 자동 주입 기능을 위한 것이다.
	// Autowired 애노테이션을 붙이면 해당 타입의 빈을 찾아서 필드에 할당한다.
	// 스프링 컨테이너는 MemberDao 타입의 빈을 memberDao 필드에 할당한다.
	// AppConf01 클래스에 MemberDao 타입의 빈을 설정했으므로 AppConf02 클래스의 memberDao 필드에는
	// AppConf01 클래스에서 설정한 빈이 할당된다.
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberPrinter memberPrinter;
	
	
	@Bean
	public MemberRegisterService memberRegService() {
		return new MemberRegisterService(memberDao);
	}
	
	@Bean
	public ChangePasswordService changePwdService() {
		
		ChangePasswordService pwdService = new ChangePasswordService();
		pwdService.setMemberDao(memberDao);
		return pwdService;
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		
		return new MemberListPrinter(memberDao, memberPrinter);
		
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		
		memberInfoPrinter.setMemberDao(memberDao);
		memberInfoPrinter.setPrinter(memberPrinter);
		return memberInfoPrinter;
		
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		
		VersionPrinter versionPrinter = new VersionPrinter();
		
		versionPrinter.setMajorVersion(3);
		versionPrinter.setMinorVersion(12);
		
		return versionPrinter;
	}	
}
