package org.aexp.springbootcouchbase.mvc.service;

import org.aexp.springbootcouchbase.mvc.model.Student;
import org.aexp.springbootcouchbase.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViewController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public void addNew() {
        userRepo.deleteAll();
        Student s = new Student("8", "xxx", 30);
        Student s2 = new Student("64", "yyy", 35);
        Student s3 = new Student("80", "John", 40);
        Student s4 = new Student("88", "luke", 44);
        Student s5 = new Student("45", "Jackie", 45);
        userRepo.save(s);
        userRepo.save(s2);
        userRepo.save(s3);
        userRepo.save(s4);
        userRepo.save(s5);
    }

    @RequestMapping(path="/addrec", method = RequestMethod.POST)
    public void add(@RequestBody Student stu){
        userRepo.save(stu);
    }

    @RequestMapping(path = "/del", method = RequestMethod.DELETE)
    public void del() {
        userRepo.deleteAll();
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Student> fetchRecords() {
        return userRepo.findAll();
    }

    @RequestMapping(path = "/get/{name}", method = RequestMethod.GET, produces = "application/json")
    public List<Student> fetchByName(@PathVariable("name") String name) {
        
        return userRepo.findByName(name);
    }

    @RequestMapping(path = "/fetch-by-query", method = RequestMethod.GET, produces = "application/json")
    public List<Student> fetchByQuery() {
       return userRepo.findByTheQuery("xxx");
    }

    @RequestMapping(path = "/custom-query", method = RequestMethod.GET, produces = "application/json")
    public List<Student> fetchCustom() {
        return userRepo.getAllOrderAndGroup();
    }

    @RequestMapping(path="/ip-from-hostname/{ifh}", method = RequestMethod.GET, produces = "application/json")
    public String ipFromHostname(@PathVariable("ifh") String ifh){
        long a=System.currentTimeMillis();
        JavaLookup.lookup(ifh);
        return System.currentTimeMillis() - a + "";
    }

    @RequestMapping(path="/hostname-from-ip/{hfi}", method = RequestMethod.GET, produces = "application/json")
    public String hostnameFromIp(@PathVariable("hfi") String hfi){
        long a=System.currentTimeMillis();
        JavaLookup.lookup(hfi);
        return System.currentTimeMillis() - a + "";
    }
}
