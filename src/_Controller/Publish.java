package _Controller;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

import _Model.Model;

public class Publish {
	
	public static void Antenne(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Antenne());
		System.out.println("Übertrage Antenne");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void Form(String a) {
		SubmissionPublisher<String> String_Pb = new SubmissionPublisher<>();
		String_Pb.subscribe(new Subscribe.Form());
		System.out.println("Übertrage Form");
		String_Pb.submit(a);
		String_Pb.close();
	}
	
	public static void Anzahl(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Anzahl());
		System.out.println("Übertrage Anzahl");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void dLambda(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.dLambda());
		System.out.println("Übertrage dLambda");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void Richtung(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Richtung());
		System.out.println("Übertrage Richtung");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
	
	public static void Amplitude(Integer a) {
		SubmissionPublisher<Integer> Integer_Pb = new SubmissionPublisher<>();
		Integer_Pb.subscribe(new Subscribe.Amplitude());
		System.out.println("Übertrage Amplitude");
		Integer_Pb.submit(a);
		Integer_Pb.close();
	}
}
