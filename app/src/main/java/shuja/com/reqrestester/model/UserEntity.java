package shuja.com.reqrestester.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserEntity {
    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("total")
    private Integer total;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("data")
    private List<Datum> data = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
