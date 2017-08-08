package cn.edu.cup.lps.hydraulic

import grails.gorm.DetachedCriteria
import org.apache.commons.lang.builder.HashCodeBuilder

class HydraulicEdge implements Serializable {

    private static final long serialVersionUID = 1

    HydraulicVertex start
    HydraulicVertex end

    @Override
    boolean equals(other) {
        if (other instanceof HydraulicEdge) {
            other.start.id == start?.id && other.end.id == end?.id
        }
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        builder.append start.id
        builder.append end.id
        builder.toHashCode()
    }

    static HydraulicEdge get(long startId, long endId) {
        criteriaFor(startId, endId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long startId, long endId) {
        HydraulicEdge.where {
            start == HydraulicVertex.load(startId) &&
                    end == HydraulicVertex.load(endId)
        }
    }

    static HydraulicEdge create(HydraulicVertex start, HydraulicVertex end) {
        def instance = new HydraulicEdge(start: start, end: end)
        instance.save()
        instance
    }

    static boolean remove(HydraulicVertex u, HydraulicVertex r) {
        if (u != null && r != null) {
            HydraulicEdge.where { start == u && end == r }.deleteAll()
        }
    }

    static int removeAll4Start(HydraulicVertex s) {
        s == null ? 0 : HydraulicEdge.where { start == s }.deleteAll()
    }

    static int removeAll4End(HydraulicVertex e) {
        e == null ? 0 : HydraulicEdge.where { end == e }.deleteAll()
    }

    static constraints = {
    }

    static mapping = {
        id composite: ['start', 'end']
        version false
    }

    String toString() {
        return "${start}-${end}"
    }
}
