package com.apple.lab9_653380120_2_sec1.Controller;

import com.apple.lab9_653380120_2_sec1.Model.AuthorDTO;
import com.apple.lab9_653380120_2_sec1.Service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    final
    AuthorService authorService;

    public WebController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors",authorService.getAllAuthors());
        return "all-authors-information";
    }

    @GetMapping("/add")
    public String createAuthor(Model model) {
        AuthorDTO authorDTO = new AuthorDTO();
        model.addAttribute("author", authorDTO);
        return "create-author";
    }

    @PostMapping("/add")
    public String createAuthor(@ModelAttribute("author") AuthorDTO authorDTO) {
        authorService.createAuthor(authorDTO);
        return "redirect:/authors";
    }

    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") Long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        return "update-author-information";
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute AuthorDTO authorDTO) {
        System.out.println("author ID ----->>> : "+authorDTO.getId());
        authorService.updateAuthor(authorDTO.getId(), authorDTO);
        return "redirect:/authors";
    }

    @GetMapping("/search")
    public String searchAuthorById(@RequestParam("id") Long id, Model model) {
        try {
            model.addAttribute("author", authorService.getAuthorById(id));
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Author not found with id " + id);
        }
        return "author-information";
    }

    @GetMapping("/view")
    public String viewAuthor(@RequestParam("id") Long id, Model model) {
        try {
            model.addAttribute("author", authorService.getAuthorById(id));
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Author not found with id " + id);
        }
        return "author-information";
    }

    @PostMapping("/delete")
    public String deleteAuthor(@RequestParam("id") Long id, Model model) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
