import javax.sound.midi.*;
import java.util.*;

class Sonido{
	
	private Synthesizer synthe = null;
	private int intensity = 100;
	private MidiChannel [] channels;
	private MidiChannel channel;


	public Sonido(){
		try{
			synthe = MidiSystem.getSynthesizer();

		}catch(Exception e){
			System.out.println("Error: al obtener synthe.");
		}
	}

	public void iniciar(){
		try{
			synthe.open();
			channels = synthe.getChannels();

		}catch(Exception e){
			System.out.println("Error: al inizializar el synthe.");
		}
	}


	public void reproducir(int nota, int canal, int duracion){
		
		channel = channels[canal];
		channel.programChange(256,127);
		channel.noteOn(nota, intensity);

		try{
				Thread.sleep(duracion);
			}catch(Exception e){
				System.out.println("ERROR: en sleep.");
			}

		channel.noteOff(nota);
	}

	public void finalizar(){
		try{
			synthe.close();

		}catch(Exception e){
			System.out.println("Error: al finalizar el synthe.");
		}
	}
}