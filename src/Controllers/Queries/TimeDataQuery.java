package Controllers.Queries;

import Model.DBEnums.DateEnum;
import Model.DBEnums.attributes.Attribute;
import Views.MetricType;
import Views.ViewPresets.AttributeType;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Class which contains a a query for a series of data in terms of date.
 */
public class TimeDataQuery extends Query {

    private DateEnum granularity;

    public TimeDataQuery(TimeQueryBuilder b) {
        super(b);
        this.granularity = b.getGranularity();
    }

    public DateEnum getGranularity() {
        return granularity;
    }

    public TimeDataQuery deriveQuery(MetricType metric) {
        TimeQueryBuilder newBuilder = new TimeQueryBuilder(metric);
        TimeDataQuery newQuery = newBuilder.granularity(this.granularity)
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .filters(this.getFilters())
                .build();

        return newQuery;

    }

    @Override
    public int hashCode() {
        return granularity.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(Object o2) {
        if (!(o2 instanceof TimeDataQuery)) {
            return false;
        }
        TimeDataQuery query2 = (TimeDataQuery) o2;
        boolean dateEquals = this.getStartDate().equals(query2.getStartDate()) && this.getEndDate().equals(query2.getEndDate());
        boolean granularityEquals = this.granularity.equals(query2.getGranularity());
        boolean filterEquals = this.getFilters().entrySet().equals(query2.getFilters().entrySet());
        boolean metricEquals = this.getMetric().equals(query2.getMetric());

        return dateEquals && granularityEquals && filterEquals && metricEquals;
    }
}
