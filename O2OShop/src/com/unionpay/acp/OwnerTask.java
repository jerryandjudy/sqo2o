package com.unionpay.acp;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public abstract class OwnerTask extends TimerTask {

	protected Timer timer;
	//最多执行次数 小于等于0时不限次数
	protected int maxTimes;
	//当前执行次数
	protected int currentTimes;
	//执行延迟时间  单位 毫秒
	protected long period;
	
	private boolean running = false;
	
	public OwnerTask(int maxTimes, int period){
		this.maxTimes = maxTimes;
		this.period = period;
		this.currentTimes = 0;
	}
	
	public void start(){
		if(maxTimes >0 && maxTimes<=currentTimes){
			this.cancel();
			timer.cancel();
			return;
		}
		timer = new Timer();
		timer.schedule(this, period);
		currentTimes++;
		
	}
	
	
	/**
	 * 在这里实现定时任务处理内容
	 */
	@Override
	public void run(){
		setRunning(true);
		try{
			taskrun();
		}finally{
			setRunning(false);
		}
	}
	
	public abstract void taskrun();
	
	protected void setRunning(boolean running){
		this.running = running;
	}
	
	public void stop(){
		timer.cancel();
		while(running){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Timer 被schedule后state修改为1不可再被schedule,所以这里在stop时把state改为0
		Field [] fields = TimerTask.class.getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			if(field.getName().equals("state")){
				try {
					field.set(this, 0);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void reStart(){
		if(currentTimes > 1)
			period = (period/(currentTimes-1)) * currentTimes;
		start();
	}
	
}
