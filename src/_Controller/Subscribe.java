package _Controller;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import _Model.Model;

public class Subscribe {
	
	private static Subscription subscription;
	
	public static class Antenne implements Subscriber<Integer>{
		
		@Override
		public void onComplete() {
			// Aktualisierung ausführen!!!!
			System.out.println("Übertragung Antennen Art nach Model erfolgreich"); 
		}

		@Override
		public void onError(Throwable error) {
			System.out.println("Fehler ist aufgetreten");
		}

		@Override
		public void onNext(Integer item) {
			Model.Antenne = item;
			System.out.println("Empfange Antennen Art: " + item);
		    subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			Subscribe.subscription = subscription;
	        subscription.request(1);
		}
	}
	public static class Form implements Subscriber<String>{
		
		@Override
		public void onComplete() {
			// Aktualisierung ausführen!!!!
			System.out.println("Übertragung Antennen Aufstellung nach Model erfolgreich"); 
		}

		@Override
		public void onError(Throwable error) {
			System.out.println("Fehler ist aufgetreten");
		}

		@Override
		public void onNext(String item) {
			Model.Form = item;
			System.out.println("Empfange Antennen Aufstellung: " + item);
		    subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			Subscribe.subscription = subscription;
	        subscription.request(1);
		}
	}
	public static class Anzahl implements Subscriber<Integer>{
		
		@Override
		public void onComplete() {
			// Aktualisierung ausführen!!!!
			System.out.println("Übertragung Anzahl Antennen nach Model erfolgreich"); 
		}

		@Override
		public void onError(Throwable error) {
			System.out.println("Fehler ist aufgetreten");
		}

		@Override
		public void onNext(Integer item) {
			Model.Anzahl = item;
			System.out.println("Empfange Anzahl Antennen: " + item);
		    subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			Subscribe.subscription = subscription;
	        subscription.request(1);
		}
	}
	public static class dLambda implements Subscriber<Integer>{
		
		@Override
		public void onComplete() {
			// Aktualisierung ausführen!!!!
			System.out.println("Übertragung dLambda nach Model erfolgreich"); 
		}

		@Override
		public void onError(Throwable error) {
			System.out.println("Fehler ist aufgetreten");
		}

		@Override
		public void onNext(Integer item) {
			Model.dLambda = item;
			System.out.println("Empfange dLambda: " + item);
		    subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			Subscribe.subscription = subscription;
	        subscription.request(1);
		}
	}
	public static class Richtung implements Subscriber<Integer>{
		
		@Override
		public void onComplete() {
			// Aktualisierung ausführen!!!!
			System.out.println("Übertragung Antennen Richtung nach Model erfolgreich"); 
		}

		@Override
		public void onError(Throwable error) {
			System.out.println("Fehler ist aufgetreten");
		}

		@Override
		public void onNext(Integer item) {
			Model.Richtung = item;
			System.out.println("Empfange Antennen Richtung: " + item);
		    subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			Subscribe.subscription = subscription;
	        subscription.request(1);
		}
	}
	public static class Amplitude implements Subscriber<Integer>{
		
		@Override
		public void onComplete() {
			// Aktualisierung ausführen!!!!
			System.out.println("Übertragung Antennen Amplitude nach Model erfolgreich"); 
		}

		@Override
		public void onError(Throwable error) {
			System.out.println("Fehler ist aufgetreten");
		}

		@Override
		public void onNext(Integer item) {
			Model.Amplitude = item;
			System.out.println("Empfange Antennen Amplitude: " + item);
		    subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			Subscribe.subscription = subscription;
	        subscription.request(1);
		}
	}
	
}


