package com.hyh.www.ExcelDemo;

//������

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerDemo {
	private static ConnectionFactory connFactory;
	private static Connection conn;
	private static Session session;
	private static Destination destination;
	private static MessageProducer producer;
	
	ProducerDemo(){
		try {
			connFactory=new ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61618");//�������ӹ���
			conn=connFactory.createConnection();//��ȡ����
			conn.start();
			session=conn.createSession(true, Session.AUTO_ACKNOWLEDGE);//����session�Ự
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage() {
		boolean result=false;
		try {
			
			destination=session.createQueue("testQueue");//����һ����Ϣ����p2p��
			//destination=session.createTopic("testTopic");//pub/sub��
			producer=session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);//��Ϣ�־û�
			if(null!=session&&null!=producer) {
				String message="hello javaee!";
				int count=11;
				System.out.println("Producer��ʼ������Ϣ��");
				while((count--)>1) {
					Thread.sleep(500);
					producer.send(session.createTextMessage("message "+count+" : "+message));
					System.out.println("Producer:"+"message "+count+" : "+message);
					session.commit();
					result=true;
				}
			}
		} catch (JMSException e2) {
			// TODO Auto-generated catch block
			try {
				session.rollback();    //�ع�
			} catch (JMSException e) {
				e.printStackTrace();
			}     
			e2.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				producer.close();
				session.close();
				conn.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(result?"Producer:��Ϣ���ͳɹ���\n":"Producer:��Ϣ����ʧ�ܣ�\n");
	}
	
	public void sendMessageExcel(int count,String[] excelCell) {
		boolean result=false;
		try {
			
			destination=session.createQueue("testQueue");//����һ����Ϣ����p2p��
			//destination=session.createTopic("testTopic");//pub/sub��
			producer=session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);//��Ϣ�־û�
			if(null!=session&&null!=producer) {
				System.out.println("Producer��ʼ������Ϣ��");
				int n=0;
				while(n<count) {
					Thread.sleep(50);
					producer.send(session.createTextMessage(excelCell[n]));
					System.out.println("Producer:"+excelCell[n]);
					n++;
					session.commit();
					result=true;
				}
			}
		} catch (JMSException e2) {
			// TODO Auto-generated catch block
			try {
				session.rollback();    //�ع�
			} catch (JMSException e) {
				e.printStackTrace();
			}     
			e2.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				producer.close();
				session.close();
				conn.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(result?"Producer:��Ϣ���ͳɹ���\n":"Producer:��Ϣ����ʧ�ܣ�\n");
	}
	
	public static void main(String[] args) {
		ProducerDemo cd=new ProducerDemo();
		cd.sendMessage();
	}	
}
