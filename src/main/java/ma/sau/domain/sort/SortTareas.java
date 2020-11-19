package ma.sau.domain.sort;

import java.util.Comparator;

import ma.sau.domain.Tarea;

public class SortTareas implements Comparator<Tarea> {
	public int compare(Tarea a, Tarea b) {
		return (int) (a.getId() - b.getId());
	}
}
