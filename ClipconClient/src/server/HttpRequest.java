package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import lombok.Getter;

@Getter
public class HttpRequest {

	// ������ �⺻ context root. ��û ������ ���� �ڿ� ���� �پ� ���� �ٸ� url�� ���� ��û�Ѵ�.
	public static final String SERVER_CONTEXT_ROOT = "http://localhost:8080/ClipconServerProject/";
	// ��û ���� ���� key��
	public static final String TYPE_OF_REQUEST = "request_type";

	// ��û ���� (���� ��� �߰� ����)
	public static final String REQUEST_TEST = "";				// �׽�Ʈ��
	public static final String REQUEST_SIGN_IN = "signIn";		// �α��� ��û
	public static final String REQUEST_SIGN_UP = "signUp";		// ȸ������ ��û
	// TODO: Ŭ���̾�Ʈ ��û ���� �߰��ϱ�

	private HttpURLConnection conn;
	private String requestType = null;				// ��û�� ����
	private JSONObject json = new JSONObject();

	public HttpRequest(String requestType) {
		this.requestType = requestType;
		json.put(TYPE_OF_REQUEST, requestType);
	}

	public int send() {
		try {
			/* request �۽� �� */
			URL url = new URL(SERVER_CONTEXT_ROOT + requestType); 	// url ����
			conn = (HttpURLConnection) url.openConnection(); 		// ���� �غ�
			/*
			 * TODO ���� �߰� �� ���� �ʿ�. ��� ���� ���� �ʿ�
			 */
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST"); // request to post
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Clipcon", "ture");
			// configuration ~
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(1000); 	// ~ configuration
			conn.connect(); // ����

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write("msg=" + json.toString()); // send post message.
			wr.flush();
			wr.close();

			/* response ���� �� */
			int HttpResult = conn.getResponseCode(); // �������� ���� ���� �ڵ�

			// TODO: Ŭ���̾�Ʈ�� �������� ���� �������� ������ ������ ���� �� �κ��� ������ �޶���
			String result = ""; // �������� ���� ���� �޽���
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					result = result + line + "\n";
					System.out.println(line + "\n");
				}
				System.out.println("result: " + result);
				br.close();
			}
			return conn.getResponseCode(); // return response code.

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String getResponseMessage(String type) {
		try {
			return new JSONObject(conn.getResponseMessage()).get(type).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addProperty(String key, String value) {
		json.put(key, value);
	}

	/* here is example code to send message to server. */
	public static void main(String[] args) {
		// create HttpRequest and insert 'request type'.
		// for example, [sign in], [sign up], [upload file]...
		HttpRequest msg = new HttpRequest(HttpRequest.REQUEST_TEST);
		// add property as you want.
		msg.addProperty("id", "ttjs2008");
		msg.addProperty("password", "1234");
		msg.addProperty("filesize", "1024000");
		msg.addProperty("filename", "picture.jpg");
		// and send. this method return a server's http response code.
		int status = msg.send();

		// you can handle the response like this.
		switch (status) {
		case HttpURLConnection.HTTP_OK:
			break;
		default:
			break;
		}

		// or handled response code like this.
		if (status == HttpURLConnection.HTTP_OK) {
			// normal
		} else {
			// exception
		}
		// and you also get response message from server like this.
		String responseMsg = msg.getResponseMessage("error"); // parameter is type of response
		System.out.println(status + " " + responseMsg);
	}
}