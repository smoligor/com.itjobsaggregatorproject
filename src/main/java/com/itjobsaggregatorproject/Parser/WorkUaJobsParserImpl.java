package com.itjobsaggregatorproject.Parser;

import com.itjobsaggregatorproject.Entity.Company;
import com.itjobsaggregatorproject.Entity.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorkUaJobsParserImpl implements JobsParser {
    @Override
    public List<Job> parseJobs() {

        Set<String> jobLinks = new HashSet<>();
        List<String> pageLinks = new ArrayList<>();

        List<Job> jobs = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.work.ua/ua/jobs-kyiv-it/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select("li");
        String lastPageNumberRawString = links.stream().map(e -> e.toString()).collect(Collectors.toList()).stream().
                filter(e -> e.contains("/ua/jobs-kyiv-it/?page=")).collect(Collectors.toList()).get(2);
        lastPageNumberRawString = lastPageNumberRawString.substring(lastPageNumberRawString.indexOf("а "), lastPageNumberRawString.indexOf("а ") + 5);
        String parsedString = lastPageNumberRawString.replace("а ", "");
        int numberOfLastPage = Integer.valueOf(parsedString);
        for (int i = 1; i <= numberOfLastPage; i++) {
            pageLinks.add("https://www.work.ua/ua/jobs-kyiv-it/?page=" + i);
        }
        pageLinks.forEach(e -> jobLinks.addAll(parseJobLinksFromPage(e)));
        for (String jobLink : jobLinks) {
            Job job = new Job();
            parseJob(jobLink, job);
            jobs.add(job);
        }
        return jobs;
    }

    private List<String> parseJobLinksFromPage(String jobPageLink) {
        Document doc = null;
        try {
            doc = Jsoup.connect(jobPageLink).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        List<String> jobLinks = links.stream().map(link -> link.attr("abs:href")).filter
                (e -> e.length() == 36).collect(Collectors.toList());
        return jobLinks;
    }

    private void parseJob(String jobLink, Job job) {
        Document doc = null;
        try {
            doc = Jsoup.connect(jobLink).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.select("dl");
        Element jobDescriptionList = elements.first();

        Company company = new Company();
        job.setLink(jobLink);
        company.setName(jobDescriptionList.select("dt:contains(Компанія:)+dd").first().text());
        String companyLink = "https://www.work.ua/" + jobDescriptionList.select("a").attr("href").toString();
        company.setLink(companyLink);
        Elements contacts = jobDescriptionList.select("dt:contains(Контактна особа:)+dd");
        if (!contacts.isEmpty()) {
            job.setContact(contacts.first().text());
        }
        Elements contactPhones = jobDescriptionList.select("dt:contains(Телефон:)+dd");

        if (!contactPhones.isEmpty()) {
            job.setContactNumber(contactPhones.first().text());
        }
        job.setHeader(doc.select("h1").first().text());
        if (job.getLink().contains("ua")) {
            job.setCountry("Украина");
        }
        Elements cities = jobDescriptionList.select("dt:contains(Місто:)+dd");
        if (!cities.isEmpty())
            job.setCity(cities.first().text());
        job.setSendResumeLink("stub");
        job.setDescription(doc.select(".overflow").first().text());
    }
}





