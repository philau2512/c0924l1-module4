package com.example.ajax_ung_dung_blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Content is required")
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotBlank(message = "Author is required")
    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    // Function để nhận các trích đoạn
    public String getExcerpt() {
        if (content == null || content.isBlank()) {
            return "";
        }

        // Xử lý dòng trắng và xuống dòng
        String plainText = content.replaceAll("\\s+", " ").trim();
        String[] words = plainText.split(" ");

        // Nếu tổng từ <= 6 thì trả lại luôn
        if (words.length <= 6) {
            return plainText;
        }

        // Lấy 3 từ đầu và 3 từ cuối
        String firstPart = String.join(" ", Arrays.copyOfRange(words, 0, 3));
        String lastPart = String.join(" ", Arrays.copyOfRange(words, words.length - 3, words.length));

        return firstPart + " ... " + lastPart;
    }
}
