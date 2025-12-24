package com.example.Gym.controller;

import com.example.Gym.model.*;
import com.example.Gym.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * <class>AppController</class>
 * <summary>Главный управляющий центр (контроллер) сайта.</summary>
 * <description>
 * Принимает запросы от пользователя,
 * проверяет введенные данные на ошибки и решает, какую страницу показать дальше.
 * </description>
 */
@Controller
public class AppController {

    /** <summary>Инструменты для связи с таблицами в базе данных.</summary> */
    @Autowired private CoachRepository coachRepo;
    @Autowired private ClientRepository clientRepo;
    @Autowired private SpecializationRepository specRepo;

    /**
     * <summary>Открытие главной страницы.</summary>
     * <param name="model">Объект для передачи данных (цифр) на экран.</param>
     * <returns>Главная страница index.html.</returns>
     * <remarks>Здесь программа считает, сколько всего записей в базе, чтобы вывести статистику.</remarks>
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("coachCount", coachRepo.count());
        model.addAttribute("clientCount", clientRepo.count());
        model.addAttribute("specCount", specRepo.count());
        return "index";
    }

    // БЛОК РАБОТЫ С КЛИЕНТАМИ

    /**
     * <summary>Показ списка клиентов с функцией поиска.</summary>
     * <param name="search">Текст, который пользователь ввел в строку поиска.</param>
     * <description>Если строка поиска пустая — показывает всех, если заполнена — ищет по ФИО.</description>
     */
    @GetMapping("/clients")
    public String listClients(@RequestParam(value="search", required=false) String search, Model model) {
        List<Client> items = (search != null && !search.isEmpty()) ?
                clientRepo.findByFullNameContainingIgnoreCase(search) : clientRepo.findAll();
        model.addAttribute("items", items);
        return "clients-list";
    }

    /**
     * <summary>Сохранение данных клиента.</summary>
     * <description>
     * Выполняет три важные проверки:
     * 1. Правильно ли заполнены поля (буквы в ФИО, 11 цифр в телефоне).
     * 2. Не занят ли этот номер телефона другим клиентом (защита от дубликатов).
     * 3. Если всё в порядке — сохраняет данные в базу.
     * </description>
     */
    @PostMapping("/clients/save")
    public String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model) {
        if (client.getId() == null && clientRepo.existsByPhone(client.getPhone())) {
            result.rejectValue("phone", "error.client", "Клиент с таким номером уже существует!");
        }
        if (result.hasErrors()) {
            model.addAttribute("specs", specRepo.findAll());
            return "client-form";
        }
        clientRepo.save(client);
        return "redirect:/clients";
    }

    // БЛОК РАБОТЫ С ТРЕНЕРАМИ

    /**
     * <summary>Показ списка тренеров с функцией поиска.</summary>
     * <param name="search">Текст для поиска тренера по ФИО.</param>
     */
    @GetMapping("/coaches")
    public String listCoaches(@RequestParam(value="search", required=false) String search, Model model) {
        List<Coach> items = (search != null && !search.isEmpty()) ?
                coachRepo.findByFullNameContainingIgnoreCase(search) : coachRepo.findAll();
        model.addAttribute("items", items);
        return "coaches-list";
    }

    /**
     * <summary>Сохранение данных тренера.</summary>
     * <remarks>Аналогично клиентам, проверяет уникальность номера телефона перед записью в базу.</remarks>
     */
    @PostMapping("/coaches/save")
    public String saveCoach(@Valid @ModelAttribute("coach") Coach coach, BindingResult result, Model model) {
        if (coach.getId() == null && coachRepo.existsByPhone(coach.getPhone())) {
            result.rejectValue("phone", "error.coach", "Тренер с таким номером уже существует!");
        }
        if (result.hasErrors()) {
            model.addAttribute("specs", specRepo.findAll());
            return "coach-form";
        }
        coachRepo.save(coach);
        return "redirect:/coaches";
    }

    // БЛОК РАБОТЫ С НАПРАВЛЕНИЯМИ

    /**
     * <summary>Показ списка направлений (специализаций).</summary>
     * <remarks>Готовит пустое поле "newSpec", чтобы пользователь мог сразу добавить новое направление.</remarks>
     */
    @GetMapping("/specs")
    public String listSpecs(@RequestParam(value="search", required=false) String search, Model model) {
        List<Specialization> items = (search != null && !search.isEmpty()) ?
                specRepo.findByNameContainingIgnoreCase(search) : specRepo.findAll();
        model.addAttribute("items", items);
        if (!model.containsAttribute("newSpec")) {
            model.addAttribute("newSpec", new Specialization());
        }
        return "specs-list";
    }

    /**
     * <summary>Добавление нового направления.</summary>
     * <description>Проверяет, нет ли уже направления с таким же названием.</description>
     * <param name="ra">Используется для передачи сообщения об ошибке после перезагрузки страницы.</param>
     */
    @PostMapping("/specs/save")
    public String saveSpec(@Valid @ModelAttribute("newSpec") Specialization spec, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            List<Specialization> items = specRepo.findAll();
            model.addAttribute("items", items);
            return "specs-list";
        }
        if (specRepo.existsByName(spec.getName())) {
            ra.addFlashAttribute("error", "Направление уже существует!");
        } else {
            specRepo.save(spec);
        }
        return "redirect:/specs";
    }

    // ФОРМЫ СОЗДАНИЯ И РЕДАКТИРОВАНИЯ

    @GetMapping("/coaches/new") public String addCoach(Model model) { model.addAttribute("coach", new Coach()); model.addAttribute("specs", specRepo.findAll()); return "coach-form"; }
    @GetMapping("/coaches/edit/{id}") public String editCoach(@PathVariable Integer id, Model model) { model.addAttribute("coach", coachRepo.findById(id).orElseThrow()); model.addAttribute("specs", specRepo.findAll()); return "coach-form"; }
    @GetMapping("/clients/new") public String addClient(Model model) { model.addAttribute("client", new Client()); model.addAttribute("specs", specRepo.findAll()); return "client-form"; }
    @GetMapping("/clients/edit/{id}") public String editClient(@PathVariable Integer id, Model model) { model.addAttribute("client", clientRepo.findById(id).orElseThrow()); model.addAttribute("specs", specRepo.findAll()); return "client-form"; }
    @GetMapping("/specs/edit/{id}") public String editSpec(@PathVariable Integer id, Model model) { model.addAttribute("spec", specRepo.findById(id).orElseThrow()); return "spec-edit-form"; }
    @PostMapping("/specs/update") public String updateSpec(@Valid @ModelAttribute("spec") Specialization spec, BindingResult result, Model model) {
        if (result.hasErrors()) return "spec-edit-form";
        specRepo.save(spec);
        return "redirect:/specs";
    }

    // БЛОК БЕЗОПАСНОГО УДАЛЕНИЯ

    /**
     * <summary>Безопасное удаление клиента.</summary>
     * <remarks>Если клиент уже что-то купил или записался на занятие, программа поймает ошибку и просто выведет предупреждение.</remarks>
     */
    @GetMapping("/clients/delete/{id}") public String deleteClient(@PathVariable Integer id, RedirectAttributes ra) { try { clientRepo.deleteById(id); } catch (Exception e) { ra.addFlashAttribute("error", "Ошибка удаления: клиент связан с другими данными!"); } return "redirect:/clients"; }
    @GetMapping("/coaches/delete/{id}") public String deleteCoach(@PathVariable Integer id, RedirectAttributes ra) { try { coachRepo.deleteById(id); } catch (Exception e) { ra.addFlashAttribute("error", "Ошибка удаления: тренер связан с другими данными!"); } return "redirect:/coaches"; }
    @GetMapping("/specs/delete/{id}") public String deleteSpec(@PathVariable Integer id, RedirectAttributes ra) { try { specRepo.deleteById(id); } catch (Exception e) { ra.addFlashAttribute("error", "Ошибка удаления: направление используется сотрудниками!"); } return "redirect:/specs"; }
}