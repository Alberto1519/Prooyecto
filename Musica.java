package Paquetes.Metodos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public class Musica{

	String nombre;
	Clip clip;

	public Musica(String nombre){
		this.nombre = nombre;
	}

	public void playMusic(){

		try{
			File msc = new File(nombre);

			AudioInputStream audioIS = AudioSystem.getAudioInputStream(msc);
			clip = AudioSystem.getClip();
			clip.open(audioIS);
			clip.start();
			if(nombre == "./musica/BlueMonday.wav"){
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

		}catch(Exception e){
			System.out.println("Error al reproducir la cancion.");
		}
	}

	public void StopPlaying(){
		clip.stop();
	}
}