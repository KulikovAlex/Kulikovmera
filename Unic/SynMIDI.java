
import javax.sound.midi. *; 
import java.io.*;
class SynMIDI extends Thread{

public Sequence synth(){ 
Sequence seq = null;
try{
// ������������������ ����� ����������� �� 10 
// MIDI-������� �� ���� ������������� � �������� 
seq = new Sequence(Sequence.PPQ, 25); 
// ������� � ������������������ ���� ������� 
Track tr = seq.createTrack();
for (int k = 0; k < 100; k++){
ShortMessage msg = new ShortMessage();
// ��������� MIDI-���� �� ������ 10 �� 109 
msg.setMessage(ShortMessage.NOTE_ON, 10+k, 93);
// ����� ����������� ���� ����� ������ 5 �������� 
tr.add(new MidiEvent(msg, 4*k));
msg = null;
}
} catch (Exception e) { 
System.err.println("From synth(): "+e);
System.exit (0);
}
return seq;
}
public void play (Sequence seq, OutputStream os) { 
try{
Sequencer sequencer = MidiSystem.getSequencer(); 
if (sequencer == null){
System.err.println("Sequencer is not supported"); 
System.exit(0); 
}
sequencer.open(); 
sequencer.setSequence(seq); 
sequencer.startRecording();
int[] type = MidiSystem.getMidiFileTypes(seq); 
MidiSystem.write(seq, type[0], os); 
os.flush();
}catch(Exception e) {
System.err.println("From play(): " + e); 
} 
} 

public void run(){
	play(synth(),ServerSound.output);
}

}