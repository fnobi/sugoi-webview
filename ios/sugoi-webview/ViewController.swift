//
//  ViewController.swift
//  sugoi-webview
//
//  Created by fujisawa-shin on 2014/12/01.
//  Copyright (c) 2014年 Kayac Inc. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIWebViewDelegate {
    @IBOutlet weak var mainWebView: UIWebView!
    
    var URL_STRING = "http://fnobi.github.io/sugoi-webview/web/"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // load webview url
        let url = NSURL(string:URL_STRING)
        let req = NSURLRequest(URL: url!)
        
        mainWebView.loadRequest(req)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func webViewDidStartLoad(webView: UIWebView) {
        UIApplication.sharedApplication().networkActivityIndicatorVisible = true
    }
    
    func webViewDidFinishLoad(webView: UIWebView) {
        UIApplication.sharedApplication().networkActivityIndicatorVisible = false
    }
}
