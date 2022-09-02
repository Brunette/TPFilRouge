package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ReservationBLL;
import bll.UserBLL;
import bo.Reservation;
import bo.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = { "/profile", "/modifyprofile" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserBLL bll;
	private ReservationBLL bllReservation;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new UserBLL();
		bllReservation = new ReservationBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("signedin");
		if (user != null) {
			request.setAttribute("user", user);
			System.out.println("Subbed " + user.isSubNews());
			List<Reservation> reservations = bllReservation.selectByUser(user);
			request.setAttribute("reservations", reservations);
			request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
		} else {
			response.sendRedirect("acceuil");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("signedin");
		if (user != null) {
			String email = request.getParameter("email");
			String cpo = request.getParameter("cpo");
			String subscribe = request.getParameter("subscribe");
			if (email != null)
				user.setEmail(email);
			if (subscribe != null) {
				user.setSubNews((subscribe.equals("on")));
			} else {
				user.setSubNews(false);
			}
			if (cpo != null) {
				user.setCodePostal(cpo);
			}
			bll.update(user);
		}
		response.sendRedirect("profile");
	}

}
