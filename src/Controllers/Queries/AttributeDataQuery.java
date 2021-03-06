package Controllers.Queries;

import Model.DBEnums.attributes.Attribute;
import Views.MetricType;
import Views.ViewPresets.AttributeType;

import java.time.Instant;

/**
 * A query object for a query on an attribute
 */
public class AttributeDataQuery extends Query{

    private AttributeType attribute;

    public AttributeDataQuery(AttributeQueryBuilder b) {
        super(b);
        this.attribute = b.getAttribute();
    }

    public AttributeType getAttribute() {
        return attribute;
    }

    @Override
    public int hashCode() {
        return attribute.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(Object o2) {
        if (!(o2 instanceof AttributeDataQuery)) {
            return false;
        }
        AttributeDataQuery query2 = (AttributeDataQuery) o2;
        boolean dateEquals = this.getStartDate().equals(query2.getStartDate()) && this.getEndDate().equals(query2.getEndDate());
        boolean attributeEquals = this.getAttribute().equals(query2.getAttribute());
        boolean filterEquals = this.getFilters().entrySet().equals(query2.getFilters().entrySet());
        boolean metricEquals = this.getMetric().equals(query2.getMetric());

        return dateEquals && attributeEquals && filterEquals && metricEquals;
    }

    public AttributeDataQuery deriveQuery(MetricType metric, AttributeType attribute) {
        AttributeQueryBuilder newBuilder = new AttributeQueryBuilder(metric, attribute);
        return newBuilder.startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .filters(this.getFilters())
                .build();
    }
}
