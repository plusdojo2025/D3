package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@WebServlet("/QRCodeServlet")
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String storeId = request.getParameter("storeId");
		System.out.println(storeId);
		storeId = "1";
		// 保存先パス
		String saveDir = getServletContext().getRealPath("/qr");
		File dir = new File(saveDir);
		if (!dir.exists())
			dir.mkdir();

		// QRコードが存在するか
		String fileName = "store" + storeId + ".png";
		Path filePath = Paths.get(saveDir, fileName);

		if (!Files.exists(filePath)) {
			try {
				String url = "http://localhost:8080/D3/MenuServlet?store_id=" + storeId;
				
				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
				
				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);
			} catch (WriterException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("fileName", fileName);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/QRCode.jsp");
		dispatcher.forward(request, response);
	}
}
