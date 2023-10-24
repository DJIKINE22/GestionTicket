package com.GestionTicket.applications.MyTicket.Services;

import com.GestionTicket.applications.MyTicket.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

public class PaginationUtil {public static void ajouterAttributsPagination(int pageNo, Page<User> page, String sortField, String sortDir, Model model) {
    List<User> utilisateurs = page.getContent();
    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());
    model.addAttribute("compagnies", utilisateurs);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("renverseSortDirt", sortDir.equals("asc") ? "dsc" : "asc");
}
}
