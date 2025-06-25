package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import dto.Store;

@WebServlet("/QRCodeServlet")
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログイン中の店舗情報を取得
		HttpSession session = request.getSession();
		Store store = (Store) session.getAttribute("store");

		// ログインしていない場合はログイン画面へリダイレクト
		if (store == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		int storeId = store.getStore_id();
		try {
			String url = "https://plusdojo.jp/d3/MenuAccessServlet?store=" + storeId;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
			String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

			request.setAttribute("store", storeId);
			request.setAttribute("qrBase64", base64Image);
			request.getRequestDispatcher("/WEB-INF/jsp/QRCode.jsp").forward(request, response);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
}