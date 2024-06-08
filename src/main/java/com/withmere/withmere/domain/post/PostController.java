package com.withmere.withmere.domain.post;

import com.withmere.withmere.domain.post.dto.AddPostRequest;
import com.withmere.withmere.domain.post.dto.PostResponse;
import com.withmere.withmere.domain.post.dto.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse savePost(@RequestBody AddPostRequest request) {
        return postService.save(request);
    }

    @GetMapping("/category/{category}")
    public List<PostResponse> findAllByCategoryPost(@PathVariable String category) {
        return postService.findAllByCategory(Category.findByCategory(category));
    }

    @GetMapping("/{search}")
    public List<PostResponse> findAllByTitleOrContentPost(@PathVariable String search) {
        System.out.println(search);
        return postService.findAllByTitleOrContent(search, search);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
