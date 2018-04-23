package _Controller;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

import _Model.Model;

public class Publish {
	
	public static void Antenne(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Antenne());
		System.out.println("�bertrage Antenne");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void Form(String a) {
		SubmissionPublisher<String> String_Pb = new SubmissionPublisher<>();
		String_Pb.subscribe(new Subscribe.Form());
		System.out.println("�bertrage Form");
		String_Pb.submit(a);
		String_Pb.close();
	}
	
	public static void Anzahl(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Anzahl());
		System.out.println("�bertrage Anzahl");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void dLambda(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.dLambda());
		System.out.println("�bertrage dLambda");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void Richtung(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Richtung());
		System.out.println("�bertrage Richtung");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void Amplitude(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Amplitude());
		System.out.println("�bertrage Amplitude");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
}
