package uk.ac.shef.oak.com4510.model.Objects;

import java.util.List;

import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;

public class PathItem {
    private Visit visit;
    private List<VisitImages> imageList;

    public PathItem(Visit visit, List<VisitImages> imageList) {
        this.visit = visit;
        this.imageList = imageList;
    }

    public Visit getVisit() {
        return visit;
    }

    public List<VisitImages> getImageList() {
        return imageList;
    }

}