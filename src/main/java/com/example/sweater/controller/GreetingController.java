package com.example.sweater.controller;

import com.example.sweater.model.Message;
import com.example.sweater.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class GreetingController {

    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/messages")
    public String getMessages(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        return "messages";
    }

    @PostMapping("/addMessage")
    public String addMessage(@ModelAttribute Message message, Model model) {
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "redirect:/messages";
    }
}
