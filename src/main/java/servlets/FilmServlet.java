package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.CinemaBLL;
import bll.FilmBLL;
import bll.SeanceBLL;
import bo.Cinema;
import bo.Film;

/**
 * Servlet implementation class FilmServlet
 */
@WebServlet("/film")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FilmBLL bllFilm;
	private CinemaBLL bllCinema;
	private SeanceBLL bllSeance;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bllFilm = new FilmBLL();
		bllCinema = new CinemaBLL();
		bllSeance = new SeanceBLL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Film film = null;
		Cinema cinema = null;
		String filmid = request.getParameter("filmid");
		String cinemaid = request.getParameter("cinemaid");
		String dateStr = request.getParameter("date");
		System.out.println("Film doGet, id: " + filmid);
		if (filmid != null)
			film = bllFilm.selectById(Integer.valueOf(filmid));
		if (cinemaid != null)
			cinema = bllCinema.selectById(Integer.valueOf(cinemaid));
		if (film != null)
			request.setAttribute("film", film);
		else {
			System.out.println("Film Not Found: " + filmid);
		}
		if (cinema != null) {
			LocalDate date;
			if (dateStr != null) {
				date = LocalDate.parse(dateStr);
			} else {
				date = LocalDate.now();
			}
			cinema.setSeances(bllSeance.selectByCinemaFilmDay(cinema, film, date));
			System.out.println("Seance count " + cinema.getSeances().size());
			request.setAttribute("cinema", cinema);
			request.setAttribute("date", date.toString());
		}
		// 3.Acceder a la jsp
		request.getRequestDispatcher("/WEB-INF/film.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
