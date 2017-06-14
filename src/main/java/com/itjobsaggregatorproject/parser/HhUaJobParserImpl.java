package com.itjobsaggregatorproject.parser;

import com.itjobsaggregatorproject.entity.Company;
import com.itjobsaggregatorproject.entity.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component("hhUa")
public class HhUaJobParserImpl implements JobParser {
    WebDriver driver = new HtmlUnitDriver();
    final String startingUrl = "https://hh.ua/search/vacancy?enable_snippets=true&clusters=true&currency_" +
            "code=UAH&area=5&specialization=1&from=cluster_professionalArea";
    List<String> pagesList = generatePagesList();
    List<String> jobLinksList = new ArrayList<>();

    public List<Job> parseJobs(boolean isFirstParsingRoutine) {
        Set<String> jobLinks = new HashSet<>();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.MILLISECONDS);
        jobLinks.addAll(getJobLinksFromPage(startingUrl));
        int numberOfPagesToScrap = pagesList.size();
        if (!isFirstParsingRoutine) {
            numberOfPagesToScrap = numberOfPagesToScrap / 5;
        }
        for (int i = 1; i < numberOfPagesToScrap; i++) {
            jobLinks.addAll(getJobLinksFromPage(pagesList.get(i)));
        }
        jobLinksList.addAll(jobLinks);

        return parseJobsFromSource();
    }

    private List<Job> parseJobsFromSource() {
        List<Job> jobs = new ArrayList<>();
        jobLinksList.forEach(e -> {
            int index = 1;
            driver.get(e);
            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);
            Job job = new Job();
            job.setIndex(index);
            job.setLink(e);
            job.setHeader(doc.select(".title").text());
            job.setDescription(doc.select(".b-vacancy-desc-wrapper").text());
            job.setCountry("Украина");
            job.setCity(doc.select("div.l-paddings").get(4).text());
            Company company = new Company();
            company.setName(doc.select(".companyname").text());
            doc = Jsoup.parse(doc.select(".companyname").html());
            String companyLink = "https://hh.ua/"+doc.select("a").first().tagName("href").attr("href");
            company.setLink(companyLink);
            doc = Jsoup.parse(pageSource);
            driver.get(companyLink);
            job.setCompany(company);
            job.setSalary(doc.select(".l-paddings").get(4).text());
            index++;
            jobs.add(job);
        });
        return jobs;
    }

    private List<String> getJobLinksFromPage(String pageUrl) {
        driver.get(pageUrl);
        List<WebElement> elements = driver.findElements(By.className("search-result-item__name"));
        elements.stream().map(e -> e.getAttribute("href"));
        List<String> jobLinks = new ArrayList<>();
        jobLinks.addAll(elements.stream().map(e -> e.getAttribute("href")).collect(Collectors.toList()));
        return jobLinks;
    }

    private List<String> generatePagesList() {
        //generating list of pages by adding number to link
        String baseUrl = "https://hh.ua/search/vacancy?clusters=true&enable_" +
                "snippets=true&specialization=1&currency_code=UAH&area=5&page=";
        List<String> pagesList = new ArrayList<>();
        for (int i = 1; i < 99; i++) { //99 is max pages hh.ua gives on one category.
            String pageUrl;
            pageUrl = baseUrl + i;
            pagesList.add(pageUrl);
        }
        return pagesList;
    }
}


