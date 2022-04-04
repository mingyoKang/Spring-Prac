package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@Import(AppConf02.class)
// @Import({AppConf01.class, AppConf02.class})처럼 배열을 이용해 두 개 이상의 설정 클래스도 지정 가능
public class AppConfImport {
	// 두 개 이상의 설정 파일을 사용하는 또 다른 방법으로 @Import 애노테이션을 사용하는 방법
	// 나머지 하나는 AnnotationConfigApplicationContext() 생성자의 인자에 설정 클래스 목록을
	// 콤마로 구분해서 전달하는 것이었다. (AppConf01.class, AppConf02.class)
	
	// AppConfImport 설정 클래스를 사용하면, @Import 애노테이션으로 지정한 AppConf02 설정 클래스도
	// 함께 사용하기 때문에 스프링 컨테이너를 생성할 때 AppConf02 설정 클래스를 지정할 필요가 없다.

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		
		return new MemberPrinter();
		
	}	
}
