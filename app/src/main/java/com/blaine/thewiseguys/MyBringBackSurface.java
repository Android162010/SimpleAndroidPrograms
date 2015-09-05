package com.blaine.thewiseguys;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyBringBackSurface extends SurfaceView implements Runnable {
//This is a better thread class instead of using gfx java class. This class was copied and pasted into GFX surface. Was supposed to be deleted
	SurfaceHolder ourHolder;
	Thread ourThread = null ;
	boolean isRunning = true ;
	
	public MyBringBackSurface(Context context) {
	 super(context);
	 ourHolder = getHolder();
	 ourThread = new Thread(this);
	 ourThread.start();
	}

	
	public void pause(){
		isRunning = false ;	
		while(true){
		try {
			ourThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		}
		
		ourThread = null ;
	}
	
	public void resume(){
		isRunning = true ;
		 ourThread = new Thread(this);
		 ourThread.start();
		
	}
	
	@Override
	public void run() {
		while(isRunning){
			if(!ourHolder.getSurface().isValid())
				continue;
			
			Canvas canvas = ourHolder.lockCanvas();
			canvas.drawRGB(02, 02, 150);
			
			ourHolder.unlockCanvasAndPost(canvas);
		}
		
	}

}
