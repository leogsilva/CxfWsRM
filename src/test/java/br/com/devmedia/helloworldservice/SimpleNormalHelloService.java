package br.com.devmedia.helloworldservice;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class SimpleNormalHelloService implements HelloService {

	private ConcurrentHashMap<String,AtomicLong> counters = new ConcurrentHashMap<String,AtomicLong>();
	
	@Override
	public PersonInfo digaOla(PersonInfo info) {
		System.out.println("Ola " + info.getName());
		return info;
	}

	@Override
	public PersonInfo digaAdeus(PersonInfo info) {
		System.out.println("Iniciando processamento..." + Calendar.getInstance().getTime());
		info.setName("Sr. " + info.getName());
		if (info.isSleep()) {
			try {
				Thread.sleep(100 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finalizando processamento..." + Calendar.getInstance().getTime());
		return info;
	}



}