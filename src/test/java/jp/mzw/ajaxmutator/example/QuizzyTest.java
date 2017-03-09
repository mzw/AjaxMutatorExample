package jp.mzw.ajaxmutator.example;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.owasp.webscarab.model.StoreException;

import jp.mzw.revajaxmutator.test.WebAppTestBase;

/**
 * テストスイート
 * 
 * @author Yuta Maezawa
 *
 */
public class QuizzyTest extends WebAppTestBase {
	
	//--------------------------------------------------
	// ここにテストケースを実装する
	//--------------------------------------------------
	
	/** テスト対象アプリケーションの情報を記載する設定ファイル */
	public static final String APP_CONFIG = "quizzy.properties";
	
	@Test
	public void showQuizDescription() {
		By quizButton = By.id("quizzy_quiz_opt0");
		By quizDesc = By.id("quizzy_quiz_desc0");
		wait.until(ExpectedConditions.visibilityOfElementLocated(quizButton));
		driver.findElement(quizButton).click();
		Assert.assertEquals("Several quizzes are available; min/max score range is 0-100.",
				driver.findElement(quizDesc).getText().trim());
	}

	@Test
	public void startQuiz() {
		showQuizDescription();
		
		By startButton = By.id("quizzy_start_b");
		wait.until(ExpectedConditions.visibilityOfElementLocated(startButton));
		driver.findElement(startButton).click();
		
		By quizBody = By.className("quizzy_q_body");
		wait.until(ExpectedConditions.visibilityOfElementLocated(quizBody));
		Assert.assertEquals("What does AjaxMutator work for?",
				driver.findElement(quizBody).getText().trim());
	}

	//--------------------------------------------------
	// 下記は便利メソッド
	//--------------------------------------------------
	
	/**
	 * テスト対象プログラムに関する設定ファイルを読み込む
	 * ProxyサーバやWebブラウザの起動も行う
	 * 
	 * @throws IOException 設定ファイルが存在しない場合に発生
	 * @throws StoreException Proxyサーバにおいてセッションの設定に失敗した場合に発生
	 * @throws InterruptedException Proxyサーバの起動完了を待つことに失敗した場合に発生
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws IOException, StoreException, InterruptedException {
		beforeTestClass(APP_CONFIG);
	}
	
	/**
	 * 各テストケースを実行する前に
	 * 1. 設定ファイルに記述されたURLを開き
	 * 2. ページ内容の表示が完了するまで待つ
	 * 
	 */
	@Before
	public void setUp() {
		driver.get(URL);
		waitUntilShowWidgets();
	}
	
	/**
	 * ProxyサーバやWebブラウザを停止する
	 * 
	 * @throws IOException 設定ファイルが存在しない場合に発生
	 */
	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		afterTestClass(APP_CONFIG);
	}
	
}
