package com.example.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//アノテーションで付加された情報がどの段階まで保持されるかを定義
@Retention(RetentionPolicy.RUNTIME)
//アノテーションを付与できる対象を指定
@Target({ElementType.TYPE})
//javadoc コマンドなどで作成したドキュメントに反映させる為の設定
@Documented
//バリデーションを行うクラスを指定
@Constraint(validatedBy = NotBlankAnyValidator.class)
public @interface ValidateItemType {
	
	//フィールドの設定（メソッドのようにカッコを付与　default値の設定が可能）
	//チェックする値を格納するための配列.
	String[] fields();
    //特定のバリデーショングループをカスタマイズ可能にする設定（※空の Class<?> 型で初期化）
    Class<?>[] groups() default {};
    //チェック対象のオブジェクトにメタ情報を与える為の宣言
    Class<? extends Payload>[] payload()  default {};
    //エラー時に例外オブジェクトに設定されるメッセージ
    String message() default "error:may not be empty";
}
