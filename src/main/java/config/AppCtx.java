package config;

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
public class AppCtx {
	// 주입할 객체가 꼭 스프링 빈일 필요는 없다.
	// private MemberPrinter memberPrinter = new MemberPrinter(); 처럼
	// 빈으로 등록하지 않고 일반 객체로 생성해서 주입할 수도 있다.
	// 이때 차이점은 스프링 컨테이너가 객체를 관리하는지 여부이다.
	// 위와 같이 설정하면 MemberPrinter를 빈으로 등록하지 않았기에, 스프링 컨테이너에서 MemberPrinter를
	// 구할 수 없다.
	
	// 스프링 컨테이너는 자동 주입, 라이프사이클 관리 등 단순 객체 생성 외에 객체 관리를 위한
	// 다양한 기능을 제공하는데 빈으로 등록한 객체에만 기능을 적용한다.
	// 스프링 컨테이너가 제공하는 관리 기능이 필요없고 getBean() 메소드로 구할 필요가 없다면
	// 빈 객체로 꼭 등록해야하는 것은 아니다.
	
	// 최근에는 의존 자동 주입 기능을 프로젝트 전반에 걸쳐 사용하는 추세이기 때문에 의존 주입 대상은
	// 스프링 빈으로 등록하는 것이 일반적이다.

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegService() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdService() {
		
		ChangePasswordService pwdService = new ChangePasswordService();
		pwdService.setMemberDao(memberDao());
		return pwdService;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		
		return new MemberPrinter();
		
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		
		return new MemberListPrinter(memberDao(), memberPrinter());
		
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		
		memberInfoPrinter.setMemberDao(memberDao());
		memberInfoPrinter.setPrinter(memberPrinter());
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
