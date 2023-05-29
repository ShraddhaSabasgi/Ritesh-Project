package com.example.blogging.controllers;


import com.example.blogging.ServiceImpl.PostImpl;
import com.example.blogging.entity.Post;
import com.example.blogging.payloads_dto.PostDto;
import com.example.blogging.payloads_dto.PostResponse;
import com.example.blogging.services.FileService;
import com.example.blogging.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/Post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public PostDto createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto create=this.postService.createPost(postDto,userId,categoryId);
    return create;
    }

    @GetMapping("/getAll")
    public PostResponse getAllPost(@RequestParam (value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                   @RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber){
       PostResponse allPost= this.postService.getAllPost(pageSize,pageNumber);
       return allPost;
    }

    @GetMapping("/getPostById/{id}")
    public PostDto getPostById(@PathVariable Integer id){
       return this.postService.getPostById(id);
    }

    @GetMapping("/user/{userId}/posts")
    public List<PostDto> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts=this.postService.getPostsByUser(userId);
       return posts;
    }

    @GetMapping("/category/{categoryId}/posts")
    public List<PostDto> getCategoryById(@PathVariable Integer categoryId){
        List<PostDto> postDtos= this.postService.getPostsByCategory(categoryId);
        return postDtos;
    }

    @PutMapping("/update/{id}")
    public PostDto updateById(@RequestBody PostDto postDto,@PathVariable Integer id){
        PostDto update=this.postService.updatePost(postDto,id);
        return update;
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Integer id){
         this.postService.deletePost(id);
    return "delete record";
    }


    //search
    @GetMapping("/search/{keyword}")
    public List<PostDto> search(@PathVariable("keyword") String keyword){
        List<PostDto> result=this.postService.searchPostTitle(keyword);
        return result;
    }


//image
    @PostMapping("/image/{postId}")
    public PostDto uploadPostImage(@RequestParam("image")MultipartFile image,@PathVariable Integer postId) throws IOException {
        PostDto postDto=this.postService.getPostById(postId);
        String fileName= this.fileService.UploadImage(path,image);
       postDto.setImageName(fileName);
       PostDto updated=this.postService.updatePost(postDto,postId);
       return updated;
    }

    @GetMapping("/image/{imageName}")
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }

}
