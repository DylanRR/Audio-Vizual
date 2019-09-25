import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import java.awt.Color; 
import ddf.minim.*; 
import ddf.minim.signals.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AudoVizual extends PApplet {


//REF and Inspiration From
// Michael Pinn - http://www.openprocessing.org/sketch/157286
// Oggy - http://www.openprocessing.org/sketch/207474
// Lu YU - http://www.openprocessing.org/sketch/21430
// Dependencies: Minim Sound Libary




//MUSIC  


Minim minim;
AudioPlayer mySound;

String song = "Song.mp3";
float n;
float n3;
float n5;
float speed2;
Color color1 = new Color(0,204,255);
Color color2 = new Color(250,215,68);
Color color3 = new Color(17,247,79);
Color color4 = new Color(255,0,0);

//Color setup
int color1R = 0;        int color2R = 250;      int color3R = 17;      int color4R = 255;
int color1G = 204;      int color2G = 215;      int color3G = 247;     int color4G = 0;
int color1B = 255;      int color2B = 68;       int color3B = 79;      int color4B = 0;

 
//MAIN SETUP
public void setup () {
  
  surface.setResizable(true);
  
 // noCursor();
  
  background (0);
 
  //MUSIC | Add mp3 to file and change name of "hey.mp3" to your song name
  minim = new Minim(this);
  mySound = minim.loadFile(song);    
  mySound.loop();
}
 
 
public void draw () {
 
  fill(0, 20);  
  noStroke();
  rect(0, 0, width, height);
  translate(width/2, height/2);

  for (int i=0, l=1; i < mySound.bufferSize() -1; i++, l++) {
    float angle = sin(i+(n-2))*40;
    float x = sin(radians(i))*(n/angle);
    float leftLevel = mySound.left.level() * 50;
    ellipse(i, i, leftLevel, leftLevel);
    rotateZ(n*-PI/3*0.05f);
   //Color Filling
     if(l==2)
       fill(color2R, color2G, color2B, 90);
     else if(l==3)
       fill(color3R, color3G, color3B, 90);
     else if(l==4)
       fill(color4R, color4G, color4B, 90);
     else{
       fill(color1R, color1G, color1B, 90); l=1;
     }
  }
  n += 0.008f ;
  n3 += speed2;
  n5 += speed2;
}

 public void keyPressed(){
  if ( key == '.' )
    mySound.skip(1000);  // skip forward 1 second (1000 milliseconds)
  if ( key == ',' )
    mySound.skip(-1000); // skip backward 1 second (1000 milliseconds)
  if ( key == 32 &&  mySound.isPlaying())
    mySound.pause();
  else 
    mySound.play();
 }
  public void settings() {  fullScreen(P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "AudoVizual" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
