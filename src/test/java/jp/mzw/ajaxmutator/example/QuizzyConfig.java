package jp.mzw.ajaxmutator.example;

import java.io.File;
import java.io.IOException;

import com.google.common.collect.ImmutableSet;

import jp.mzw.ajaxmutator.JUnitExecutor;
import jp.mzw.ajaxmutator.MutateVisitor;
import jp.mzw.ajaxmutator.MutationTestConductor;
import jp.mzw.ajaxmutator.mutator.Mutator;
import jp.mzw.revajaxmutator.config.ConfigHelper;

/**
 * ミューテーション解析を実施するクラス
 * 
 * @author Yuta Maezawa
 *
 */
public class QuizzyConfig {

	public static void main(String[] args) throws IOException, InstantiationException {
		// ミューテーション対象のソースコード
		File target = ConfigHelper.getRecordedJsFile(QuizzyTest.APP_CONFIG);
		
		// ミューテーション箇所
		MutateVisitor visitor = MutateVisitor.defaultJqueryBuilder().build();
		// ミューテーション方法
		ImmutableSet<Mutator<?>> mutators = MutationTestConductor.defaultMutators(visitor);
		
		// ミューテーション解析の実行器の生成
		MutationTestConductor conductor = new MutationTestConductor();
		
		// 対象ソースコードにミューテーション操作を適用しミュータントを生成
		conductor.setup(target.getPath(), "", visitor);
		conductor.generateMutations(mutators);
		
		// ミュータント上でテストスイートを実行
        conductor.mutationAnalysisUsingExistingMutations(new JUnitExecutor(false, QuizzyTest.class));
	}

}
