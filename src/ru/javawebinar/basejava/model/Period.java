package ru.javawebinar.basejava.model;

import com.google.gson.annotations.JsonAdapter;
import ru.javawebinar.basejava.util.LocalDateAdapter;
import ru.javawebinar.basejava.util.LocalDateTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    public Period(){}

    public Period(String name, String description, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!name.equals(period.name)) return false;
        if (!Objects.equals(description, period.description)) return false;
        if (!startDate.equals(period.startDate)) return false;
        return Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String toString() {
        String str;
        if (description == null) str = "";
        else str = "\n   " + description;

        if (endDate == null) str = "Сейчас  " + name + str;
        else str = endDate.format(DateTimeFormatter.ofPattern("MM/yyyy")) + " " + name + str;

        return startDate.format(DateTimeFormatter.ofPattern("MM/yyyy")) + "-" + str;
    }
}
