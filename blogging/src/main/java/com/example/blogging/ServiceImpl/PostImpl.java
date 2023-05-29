package com.example.blogging.ServiceImpl;

import com.example.blogging.entity.Category;
import com.example.blogging.entity.Post;
import com.example.blogging.entity.User;
import com.example.blogging.payloads_dto.PostDto;
import com.example.blogging.payloads_dto.PostResponse;
import com.example.blogging.repository.CatergoryRepository;
import com.example.blogging.repository.PostRepository;
import com.example.blogging.repository.UserRepository;
import com.example.blogging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CatergoryRepository catergoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=this.userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Category category=this.catergoryRepository.findById(categoryId).orElseThrow(RuntimeException::new);
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savePost=this.postRepository.save(post);

        return this.modelMapper.map(savePost,PostDto.class);
    }



    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {

        Post post=this.postRepository.findById(id).orElseThrow(RuntimeException::new);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatePost=this.postRepository.save(post);
        return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public PostResponse getAllPost(Integer pageSize, Integer pageNumber) {

        Pageable pageable= PageRequest.of(pageSize,pageNumber);
        Page<Post> postPage=this.postRepository.findAll(pageable);
        List<Post> allPostsPage=postPage.getContent();

//        List<Post> allPost=this.postRepository.findAll();
        List<PostDto> all=allPostsPage.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(all);
        postResponse.setPageSize(postPage.getSize());
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer id)
    {
        Post post=this.postRepository.findById(id).orElseThrow(RuntimeException::new);

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category=this.catergoryRepository.findById(categoryId).orElseThrow(RuntimeException::new);
        List<Post> posts= this.postRepository.findByCategory(category);
        List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(RuntimeException::new);
        List<Post> posts=this.postRepository.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPostTitle(String keyword) {
        List<Post> posts=this.postRepository.findByTitleContaining(keyword);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
