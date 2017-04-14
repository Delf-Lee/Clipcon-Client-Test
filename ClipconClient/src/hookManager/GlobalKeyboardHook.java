package hookManager;

import java.util.ArrayList;


public class GlobalKeyboardHook {
	// ----------- Java Native methods -------------
	  
		// ������ ����Ű�� ���ȴ��� Ȯ��, �������� return true 
	    public native boolean checkHotKey();
	  
	    /**
	    * Sets the hot key.
	    * @param virtualKey Specifies the virtual-key code of the hot key.
	    * @param alt Either ALT key must be held down.
	    * @param control Either CTRL key must be held down.
	    * @param shift Either SHIFT key must be held down.
	    * @param win Either WINDOWS key was held down. These keys are labeled with the Microsoft Windows logo.
	    * Keyboard shortcuts that involve the WINDOWS key are reserved for use by the operating system.
	    * @return If the function succeeds, the return value is TRUE.
	    */
	    public native boolean setHotKey(int virtualKey, boolean alt, boolean control, boolean shift, boolean win);
	  
	    /**
	    * Resets the installed hotkeys.
	    */  
	    // ����Ű ����  
	    public native void resetHotKey();
	  
	    // dll ���� ����
	    private static final String KEYBOARD_HOOOK_DLL_NAME = "KeyHooking";
	  
	    private boolean stopFlag;
	  
	    // -------- Java listeners --------
	    private ArrayList<GlobalKeyboardListener> listeners = new ArrayList<GlobalKeyboardListener>();
	  
	    // ������ 
	    public GlobalKeyboardHook() {
	        System.loadLibrary(KEYBOARD_HOOOK_DLL_NAME);
	        System.out.println(KEYBOARD_HOOOK_DLL_NAME + ".dll ���� �ε� �Ϸ� ");
	        stopFlag = false;
	    }
	  
	    public void addGlobalKeyboardListener(GlobalKeyboardListener listener) {
	        listeners.add(listener);
	    }
	  
	    public void removeGlobalKeyboardListener(GlobalKeyboardListener listener) {
	        listeners.remove(listener);
	    }
	  
	    
	    // ��ŷ ����, DLLStateThread ���� �� DLL ���� üũ ?
	    public void startHook() {
	        stopFlag = false;
	        DLLStateThread currentWorker = new DLLStateThread();
	        Thread statusThread = new Thread(currentWorker);
	        statusThread.start();
	    }
	  
	    /**
	    * Finish the current KeyboardThreadWorker instance.
	    */
	    public void stopHook() {
	        stopFlag = true;
	    }
	  
	    /**
	    * Sends the event notification to all listeners.
	    */
	    private void fireHotkeysEvent() {
	        for (GlobalKeyboardListener listener : listeners) {
	            listener.onGlobalHotkeysPressed();
	        }
	    }
	  
	    
	    // DLL ���� üũ�κ�
	    private class DLLStateThread implements Runnable {
	      
	        public void run() {
	            for(;;) {
	                boolean hotKeyPressed = checkHotKey();
	                if (hotKeyPressed) {
	                    // hot key was pressed, send the event to all listeners
	                    fireHotkeysEvent();
	                }
	                try {
	                    Thread.sleep(100); //every 100 ms check the DLL status.
	                    // work unless stopFlag == false
	                    if (stopFlag) {
	                        resetHotKey();
	                        break;
	                    }
	                    
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
}
